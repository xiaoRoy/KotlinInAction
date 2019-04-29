package com.learn.kotlindoc.delegation

private class Contact(val name: String) {

    private var _emails: List<String>? = null

    val emails: List<String>
        get() {
            if (_emails == null) {
                _emails = loadEmails()
            }
            return _emails!!
        }
}

private class ContactWithByLazy(val name: String) {
    val emails: List<String> by lazy { loadEmails() }
}

private fun loadEmails(): List<String> {
    //do some long time operation
    return listOf()
}
