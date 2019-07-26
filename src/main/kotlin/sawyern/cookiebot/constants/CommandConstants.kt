package sawyern.cookiebot.constants

import java.util.*

class CommandConstants {
    companion object {
        val COMMAND_START = "!"
        val SPACE = " "

        val RANDOM = Random()

        val COMMAND_DELIM = " "

        val QUOTE = "\""
        val EMPTY_STRING = ""

        val QUOTE_REGEX = "([^\"]\\S*|\".+?\")\\s*"
        val DICE = "\uD83C\uDFB2"
    }

    object CommandName {
        val PING = "ping"
        val REGISTER = "register"
        val COOKIES = "cookies"
        val UNKNOWN = "unknown"
    }
}