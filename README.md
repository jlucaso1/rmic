## Dependencies

- Java 8
- nodemon (for hotreload server)

## Getting Started

run rmiregistry
```bash
cd bin
rmiregistry
```

watch and compile server

```bash
nodemon -e java -w src -x 'javac -d bin src/*.java; cd bin && rmic HelloServer && java HelloServer'
```

run client

```bash
cd bin
java HelloClient
```