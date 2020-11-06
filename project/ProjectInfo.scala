import sbt.{Developer, ScmInfo, url}

object ProjectInfo {
  val ProjectVersion: String = "0.4.0"

  val projectDevelopers: List[Developer] = List(
    Developer(
        "Kevin-Lee", "Kevin Lee"
      , "kevin.code@kevinlee.io"
      , url("https://github.com/Kevin-Lee")
    ))

  val projectHomePage: Option[sbt.URL] = Some(url("https://github.com/Kevin-Lee/j8plus"))

  val projectScmInfo: Option[ScmInfo] = Some(ScmInfo(
        url("https://github.com/Kevin-Lee/j8plus")
      , "git@github.com:Kevin-Lee/j8plus.git"
    ))
}
