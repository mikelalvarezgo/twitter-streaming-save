package twitterApps.models
import com.mongodb.casbah.commons.MongoDBObject
import org.apache.spark.streaming.twitter.TwitterUtils
import spray.json.DefaultJsonProtocol._

import twitter4j.{GeoLocation, Status, Twitter}
import twitter4j.Twitter._
import utils.mongo.Converters.{dbObject, to}
import utils.mongo.{DAO, Id, MongoDbComponent}

import scala.util.Try

case class TweetInfo(
  tweet_id: Long,
  createdAt: Long,
  geo_location: Location,
  user_id: Long,
  user_name: String,
  user_followers: Int)

case class Location(
  latitude: Double,
  longitude:Double
)
object Location {
  lazy implicit val format = jsonFormat2(Location.apply)
  type Latitude = Double
  type Longitude = Double
}

object TweetInfo {

  lazy implicit val format =  jsonFormat6(TweetInfo.apply)
  type Tweet_id = Long
  type CreatedAt = Long
  type Geo_Location = Location
  type User_Id = Long
  type User_Name = String
  type User_Followers = Int

  def content2TwitterInfo(tw: Status): TweetInfo = {
    TweetInfo(
      tw.getId,
      tw.getCreatedAt.toInstant.getNano,
      Location(
        tw.getGeoLocation.getLatitude,
        tw.getGeoLocation.getLongitude),
      tw.getUser.getId,
      tw.getUser.getName,
      tw.getUser.getFollowersCount
    )
  }

}


