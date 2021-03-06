# Scala REST API

Sample project for testing REST API using Scala

## Installation

Make sure you have already set up Scala development environment
- [JDK + SBT](https://www.scala-sbt.org/1.x/docs/Setup.html)

Go to the folder with project, compile and package
```shell
sbt clean compile && sbt package
```

## Running

To run the project, simply execute
```shell
sbt run
```

## Usage

### Maze Creation

Endpoint | `/mazes`
--- | ---
Method | **POST**
Description | Generates maze with `width` and `height` sizes

Send
```json
{
    "width": 12,
    "height": 12
}
```

Receive (in sbt console)
```json
███████████████████████████████████████████████████████████████████████████
███                                                                     ███
███   ███   ███   ███   ███████████████   █████████   ███   ███████████████
███   ███   ███   ███   ███         ███   ███         ███               ███
███   ███   █████████   ███   ███   ███   ███████████████████████████   ███
███   ███   ███         ███   ███   ███                           ███   ███
███   ███   ███   █████████   ███   ███████████████   █████████   ███   ███
███   ███   ███         ███   ███               ███   ███         ███   ███
███   ███████████████   ███   █████████████████████   █████████████████████
███               ███   ███                     ███                     ███
█████████████████████   █████████████████████   █████████   █████████   ███
███                     ███         ███   ███         ███   ███         ███
███   █████████   █████████   ███   ███   █████████   ███   ███████████████
███   ███   ███   ███         ███               ███   ███               ███
███   ███   ███   ███   █████████████████████   ███   █████████   ███   ███
███   ███         ███                     ███   ███               ███   ███
███   █████████████████████████████████   ███   █████████████████████   ███
███               ███                     ███                     ███   ███
███   █████████   ███████████████████████████   ███████████████   █████████
███         ███                           ███   ███         ███         ███
█████████   ███   █████████   ███████████████   ███   ███   █████████   ███
███   ███   ███         ███                     ███   ███         ███   ███
███   ███   █████████   ███████████████████████████   █████████   ███   ███
███               ███                                 ███         ███   ███
███████████████████████████████████████████████████████████████████████████
```