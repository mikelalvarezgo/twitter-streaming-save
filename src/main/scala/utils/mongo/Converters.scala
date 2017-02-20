package utils.mongo

import com.mongodb.DBObject
import com.mongodb.util.JSON
import spray.json.{JsonFormat, JsonParser}

object Converters {

  implicit def toMongoDBObject[I: JsonFormat, O]: I => O = i =>
    JSON.parse(implicitly[JsonFormat[I]].write(i).prettyPrint).asInstanceOf[O]

  implicit def dbObject[T: JsonFormat]: T => DBObject =
    toMongoDBObject[T,DBObject].apply

  implicit def to[T: JsonFormat]: DBObject => T = dbo =>
    implicitly[JsonFormat[T]].read(JsonParser(dbo.toString))

}
