package twitterApps
import twitter4j.Status
import models.{TweetInfo}
import utils.mongo.TweetInfoDAO


object TweetsSave extends Analytics{

  //  Add filters ...

  filter(
    "Errejon",
    "Iglesias"
  )

  //  ... add actions to perform ...

  def prueba(tweet: Status) = ???
  val tweetInfoDao: TweetInfoDAO = TweetInfoDAO(
    config.getString("mongodb.host"),
    config.getInt("mongodb.port"),
    config.getString("mongodb.database"))


  when { tweets =>
    logger.info(s"+++++++++++ ${tweets.name}" )
    val tweetsInfo = tweets.map( tweet => {
      tweetInfoDao.create(TweetInfo.content2TwitterInfo(tweet))
    })
    tweets.foreach {
      tweet => tweet.getText
        logger.warn(tweet.getText)
    }
  }
  when { tweets =>
    logger.info(s"Received tweets [${tweets.count()}}]")
  }
  // ... and begin listening
  listen()

}
