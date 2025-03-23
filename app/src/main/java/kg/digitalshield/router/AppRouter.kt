package kg.digitalshield.router

private object Route {
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val HOME = "home"
}

sealed class Screen(val route: String) {
    data object Login : Screen(Route.LOGIN)
    data object Register : Screen(Route.REGISTER)
    data object Home : Screen(Route.HOME)

}