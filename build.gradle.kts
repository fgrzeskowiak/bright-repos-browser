tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}
