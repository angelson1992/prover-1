FROM maven:3.3-jdk-8
ADD ./target/server-jar-with-dependencies.jar ./
RUN mkdir -p ./snark-20120808r02
COPY ./snark-20120808r02/ ./snark-20120808r02
EXPOSE 25333 25334
CMD java -jar server-jar-with-dependencies.jar