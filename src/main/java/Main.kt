import com.google.protobuf.CodedOutputStream
import com.rubiconmd.proto.RubiconProto
import java.io.ByteArrayOutputStream

data class Case(
        val id: String,
        val created_at: String,
        val symptoms: String?,
        val last_response: Response?
)

data class Response(
        val id: String,
        val body: String
)


fun main(args: Array<String>) {

    //Models are the same
    val response = Response(
            id = "567",
            body = "Neutral response"
    )

    val case = Case(
            id = "1234",
            created_at = "13/13/13",
            symptoms = "His only regret, is that I have, boneitis",
            last_response = response
    )

    //Only difference, map them to ProtoBuf objects
    val protoCase = RubiconProto.Case.newBuilder()
            .setId(case.id)
            .setCreatedAt(case.created_at)
            .setSymptoms(case.symptoms)
            .setLastResponse(
                    RubiconProto.Response.newBuilder()
                            .setId(response.id)
                            .setBody(response.body)
                            .build()
            )
            .build()

    //Send them over the network as a byte array
    val byteStream = ByteArrayOutputStream()
    val output = CodedOutputStream.newInstance(byteStream)
    protoCase.writeTo(output)
    output.flush()

    //Beep bop, internet activity, get the bytes
    val bytes = byteStream.toByteArray()
    val parsedProtoCase = RubiconProto.Case.newBuilder().mergeFrom(bytes)
    val parsedCase = Case(
            id = parsedProtoCase.id,
            created_at = parsedProtoCase.createdAt,
            symptoms = parsedProtoCase.symptoms,
            last_response = Response(
                    id = parsedProtoCase.lastResponse.id,
                    body = parsedProtoCase.lastResponse.body
            )
    )

    assert(parsedCase == case)
}