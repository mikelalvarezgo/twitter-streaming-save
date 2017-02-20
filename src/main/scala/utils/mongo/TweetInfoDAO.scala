package utils.mongo

import com.mongodb.casbah.commons.MongoDBObject
import twitterApps.models.TweetInfo
import utils.mongo.Converters.{dbObject, to}

import scala.util.Try

case class TweetInfoDAO(
  mongoHost: String,
  mongoPort: Int,
  db: String) extends AbsTweetInfoDAO with MongoDbComponent{


  lazy val tweet_info = database("tweets")

  override def getAll: Stream[TweetInfo] = {
    tweet_info.find().toStream.map(to[TweetInfo].apply)
  }

  def create(stateAcc: TweetInfo): Try[Unit] = Try {
    val bulk = tweet_info.initializeOrderedBulkOperation
    bulk.insert(dbObject[TweetInfo].apply(stateAcc))
    require(bulk.execute().isAcknowledged)
  }

  def update(stateAcc: TweetInfo): Try[Unit] = ???

  def remove(idAccount: Id): Try[Unit] = Try {
    val bulk = tweet_info.initializeOrderedBulkOperation
    bulk.find(MongoDBObject("id" -> idAccount.value)).remove()
    require(bulk.execute().isAcknowledged)
  }

  def get(idAcc: Id): Try[TweetInfo] = Try {
    tweet_info.findOne(MongoDBObject(
      "id" ->idAcc.value )).toStream.map(to[TweetInfo].apply).head
  }

  def get(
    page: Int,
    pageSize: Int,
    sortField: Option[String],
    sortAsc: Option[Boolean]): Try[List[TweetInfo]] = ???

  def getLatest: Try[TweetInfo] = Try {
    tweet_info.find()
      .sort(MongoDBObject("date" -> -1))
      .map(obj => to[TweetInfo].apply(obj))
      .toStream.head
  }
  override def latest[U](sorting: TweetInfo => U)(implicit o: Ordering[U]): Try[TweetInfo] = ???

}



trait AbsTweetInfoDAO extends DAO[TweetInfo] {


}
object TweetInfoDAO {

  val IdAccount = "IdCampaign"
  val Amount = "Amount"
  val Date = "Date"

}
