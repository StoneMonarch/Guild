FROM gradle:6.7-jdk15 AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY build.gradle /workspace
COPY settings.gradle /workspace
COPY gradle /workspace
COPY src /workspace/src
RUN gradle build
RUN unzip build/distributions/Nova*.zip
RUN ls build/distributions/
Run ls

FROM openjdk:15.0.1-jdk
RUN mkdir -p /Nova
COPY --from=BUILD /workspace/Nova*/bin/Nova /Nova/bin/Nova
COPY --from=BUILD /workspace/Nova*/lib/* /Nova/lib/
CMD ./Nova/bin/Nova
