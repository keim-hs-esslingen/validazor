package de.hsesslingen.keim.validazor

/**
 * This class is used to track the path of properties during validation.
 * Each instance of this path holds a reference to its parent path, allowing
 * a recursive generation of a path string by iterating back to the root path object.
 */
class PropertyPath private constructor(
    /**
     * The parent of this path node or `null` if this path node is the root node.
     */
    parent: PropertyPath?,
    /**
     * The name of this path node, i.e. the name of the property it represents.
     */
    val name: String,
    /**
     * The path separator that should be used to separate child properties from their parents
     * during string generation.
     */
    private val childSeparator: String,
    /**
     * Whether this node represents a nested node.
     * This is e.g. not the case when iterating over collection items.
     */
    private val separateFromParent: Boolean
) {
    private val _parent = parent
    private val parent: PropertyPath
        get() = _parent ?: throw Exception("Illegal access to root parent.")

    private val isRoot = (parent == null)

    private val separator: String
        get() = if (separateFromParent) childSeparator else ""

    @Suppress("GrazieInspection")
            /**
             * Derives a new path node from this node for an indexed object, e.g. an item of an [Iterable].
             */
    fun index(index: Int): PropertyPath {
        return PropertyPath(
            parent = this,
            name = "[$index]",
            childSeparator = childSeparator,
            separateFromParent = false
        )
    }

    /**
     * Derives a new path node from this node for a key object, e.g. a key of a [Map].
     */
    fun key(key: Any?): PropertyPath {
        val keyStr = if (key != null && key is String) {
            "\"$key\""
        } else {
            "$key"
        }

        return PropertyPath(
            parent = this,
            name = "[$keyStr]",
            childSeparator = childSeparator,
            separateFromParent = false
        )
    }

    /**
     * Derives a new path node from this node using a "where"-notation.
     * This will result in a notation like parent[where $propertyName=$propertyValue], which is useful
     * if items in a collection are not identified by their index but by a value of a property of their own.
     */
    fun where(propertyName: String, propertyValue: Any?): PropertyPath {
        return PropertyPath(
            parent = this,
            name = "[where $propertyName=$propertyValue]",
            childSeparator = childSeparator,
            separateFromParent = false
        )
    }

    /**
     * Derives a new path node from this node using a "where"-notation.
     * This will result in a notation like parent[where $propertyName=$propertyValue], which is useful
     * if items in a collection are not identified by their index but by a value of a property of their own.
     */
    fun where(propertyName: String, propertyValue: String): PropertyPath {
        // Adding quotation marks if called with string property value to reflect type in the output.
        return where(propertyName, "\"$propertyValue\"" as Any) // Casting to Any to call other function.
    }

    /**
     * Derives a new path node from this node for a nested property., e.g. the properties of a POJO.
     */
    fun child(name: String): PropertyPath {
        return PropertyPath(
            parent = this,
            name = name,
            childSeparator = childSeparator,
            separateFromParent = true
        )
    }

    /**
     * Converts this path to a string using the separators configured in the root node.
     */
    override fun toString(): String {
        var pathString = ""
        var current = this
        var previous = this

        while (!current.isRoot) {
            pathString = current.separator + current.name + pathString
            previous = current
            current = current.parent
        }

        if (pathString.isNotEmpty() && previous.separateFromParent) {
            pathString = pathString.substring(1)
        }

        return pathString
    }

    companion object {
        /**
         * The default path separator that is used if none other is configured.
         */
        const val DEFAULT_PATH_SEPARATOR = "."

        /**
         * Creates a new root path node.
         *
         * The specified path separator will be used during string generation to separate child nodes from parent nodes.
         */
        fun createRoot(
            pathSeparator: String = DEFAULT_PATH_SEPARATOR
        ): PropertyPath {
            return PropertyPath(
                parent = null,
                name = "",
                childSeparator = pathSeparator,
                separateFromParent = false
            )
        }
    }
}