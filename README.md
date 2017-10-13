# ProtobufSample
Protobuf sample generating Java and Ruby code

Install the ruby code gen gem with `grpc-tools` (full guide [here](https://grpc.io/docs/quickstart/ruby.html))

To build the project, execute `./gradew build`

Generated source code lives under `build/generated/source/proto/main/[javalite/ruby]` 

For this dummy object, the Protobuf byte array is 57% smaller, 84 bytes to 147 bytes.
