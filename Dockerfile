FROM gradle:6.7-jdk15 AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY build.gradle /workspace
COPY settings.gradle /workspace
COPY gradle /workspace
COPY src /workspace/src
RUN gradle build
RUN unzip build/distributions/Guild*.zip

FROM openjdk:15.0.1-jdk
RUN mkdir -p /Guild
COPY --from=BUILD /workspace/Guild*/bin/Guild /Guild/bin/Guild
COPY --from=BUILD /workspace/Guild*/lib/* /Guild/lib/
CMD ./Guild/bin/Guild
