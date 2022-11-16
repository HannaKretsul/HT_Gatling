package computerdatabase

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

  private val httpProtocol = http
    .baseUrl("https://challenge.flood.io")
    .inferHtmlResources(AllowList(), DenyList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""))
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7,uk;q=0.6")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")

 object HomePage_Start {
   val homePage_Start = exec(http("Load_Start")
       .post("/start")
       .formParam("utf8", "✓")
       .formParam("authenticity_token", "8sxo+/noP+rqfwipnT7x9TM38/jFxA9O0Q8XBtagKfQ=")
       .formParam("challenger[step_id]", "ZWp5c2tsVmJnd2dSUnV6Rjdra09Edz09LS1KTXJjTHA5TUJXTTROVHpuOG5Ka2JnPT0=--ca429c1445c3d456ce335ef6282dc9f1b8f87b52")
       .formParam("challenger[step_number]", "1")
       .formParam("commit", "Start"))
     .pause(1)
 }

  object Page_Step2 {
    val page_Step2 = exec(http("Load_Step2_age")
        .post("/start")
        .formParam("utf8", "✓")
        .formParam("authenticity_token", "8sxo+/noP+rqfwipnT7x9TM38/jFxA9O0Q8XBtagKfQ=")
        .formParam("challenger[step_id]", "WjIwZzV2VjM0QlpSd2Z3MXRkL002QT09LS15b1AyeDdycUtZc1gvQ1VpSnBzakpBPT0=--2091b8561576abe1e2e6051ebf61558b38004595")
        .formParam("challenger[step_number]", "2")
        .formParam("challenger[age]", "42")
        .formParam("commit", "Next"))
      .pause(2)
  }

    object Page_Step3 {
      val page_Step3 = exec(http("Enter_Largest_value")
          .post("/start")
          .formParam("utf8", "✓")
          .formParam("authenticity_token", "8sxo+/noP+rqfwipnT7x9TM38/jFxA9O0Q8XBtagKfQ=")
          .formParam("challenger[step_id]", "NmxkUVdzck9EdHhpRnF3d0lvZHMwdz09LS1UWmdJOENkL1V2R01xSXFwa285MVlRPT0=--2c481b7f9e452b344bc2b40019050c0b88bb5dce")
          .formParam("challenger[step_number]", "3")
          .formParam("challenger[largest_order]", "295")
          .formParam("challenger[order_selected]", "VllocitlOE5mVjlVQXY1VWtjczRmZz09LS13QmpKbFl2S3VGSlhHTkp4OGVocG13PT0=--a0c79c22ade33e12bb3fd2ea5b3c80a4b94ef89a")
          .formParam("commit", "Next"))
        .pause(2)
    }

    object Page_Step4 {
      val page_Step4 = exec(http("Load_Step4_Next")
          .post("/start")
          .formParam("utf8", "✓")
          .formParam("authenticity_token", "8sxo+/noP+rqfwipnT7x9TM38/jFxA9O0Q8XBtagKfQ=")
          .formParam("challenger[step_id]", "NFlqclJFWk1OSkpjMCtQYThIYjF6dz09LS1oWktMbkZrSXYyZ1NvQXlsVEo1RTVRPT0=--4d3c827358f7ad5b8f9e8e047156095b3389d818")
          .formParam("challenger[step_number]", "4")
          .formParam("challenger[order_4]", "1668435673")
          .formParam("challenger[order_10]", "1668435673")
          .formParam("challenger[order_4]", "1668435673")
          .formParam("challenger[order_6]", "1668435673")
          .formParam("challenger[order_8]", "1668435673")
          .formParam("challenger[order_8]", "1668435673")
          .formParam("challenger[order_11]", "1668435673")
          .formParam("challenger[order_15]", "1668435673")
          .formParam("challenger[order_17]", "1668435673")
          .formParam("challenger[order_16]", "1668435673")
          .formParam("commit", "Next")
          .resources(
            http("RecordedSimulation_4")
              .get("/code")

          )
      )
        .pause(2)
    }

  object Page_Step5 {
    val page_Step5 =  exec(http("Step5_Enter_Token")
        .post("/start")
        .formParam("utf8", "✓")
        .formParam("authenticity_token", "8sxo+/noP+rqfwipnT7x9TM38/jFxA9O0Q8XBtagKfQ=")
        .formParam("challenger[step_id]", "OEhyVHhXZXFtSVc3YkJ6NnBBSXZzZz09LS1pWE56d3AzZWwxM3o3SUd1eDlINjl3PT0=--a9a5f0b01b7e3cba2fb300cd9faafc8c0c3e068d")
        .formParam("challenger[step_number]", "5")
        .formParam("challenger[one_time_token]", "3641975248")
        .formParam("commit", "Next")
    )
  }
  val users = scenario("Users")
    .exec(HomePage_Start.homePage_Start, Page_Step2.page_Step2, Page_Step3.page_Step3, Page_Step4.page_Step4, Page_Step5.page_Step5)
  setUp(users.inject(atOnceUsers(5))).protocols(httpProtocol)
}
