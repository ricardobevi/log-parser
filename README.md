# Parsing Logs



## How to build

This program is built using `gradle`, so building it is just a matter of running:

```
gradle fullJar

```

This will build a .jar with all the needed dependencies packaged.

## How to run

#### Running a MySQL server

This application needs a MySQL database running on `localhost:3306` and a database named `parser`. 
Its also configured with user `root` and password `0000`. 

For testing purposes a MySQL server can be launched easily with Docker:

```
docker pull mysql
docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=0000 mysql
```

For changing this parameters, the file `persistence.xml` must be changed and the application re-built. This is because of a JPA limitation.

#### Creating a database with the needed schema

You should create a database named `parser` with the following schema:

```
	CREATE DATABASE IF NOT EXISTS `parser` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
	USE `parser`;

	CREATE TABLE `BlockedComments` (
	  `id` bigint(20) NOT NULL AUTO_INCREMENT,
	  `comment` varchar(255) COLLATE utf8_bin DEFAULT NULL,
	  PRIMARY KEY (`id`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
	
	
	CREATE TABLE `Request` (
	  `id` bigint(20) NOT NULL AUTO_INCREMENT,
	  `ip` varchar(255) COLLATE utf8_bin DEFAULT NULL,
	  `method` varchar(255) COLLATE utf8_bin DEFAULT NULL,
	  `requestDate` date DEFAULT NULL,
	  `statusCode` int(11) DEFAULT NULL,
	  `userAgent` varchar(255) COLLATE utf8_bin DEFAULT NULL,
	  PRIMARY KEY (`id`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
	
```

 

That should download the images and lunch this service, a postgresql database and a notification service.

#### Running from the command line

Running without arguments displays a help message:

```
$ java -jar parser.jar 

Processing...
Missing required options: startDate, duration, threshold

usage: parser.jar [--accesslog <path>] --duration <hourly,daily> --startDate <date> --threshold <int>
    --accesslog <path>          Path to the access.log file
    --duration <hourly,daily>   'hourly' or 'daily'
    --startDate <date>          Stating point to meassure in "yyyy-MM-dd.HH:mm:ss" format
    --threshold <int>           Number of request per IP to filter

```

If any required parameter is missing, it will trigger a validation.

Also, as requested, the program can be run like this:

```
$ java -cp "parser.jar" com.ef.Parser --accesslog=access.log --startDate=2017-01-01.00:00:00 --duration=hourly --threshold=200
Processing...
192.168.234.82 has 200 or more requests between 2017-01-01.00:00:00 and 2017-01-01.00:59:59
192.168.169.194 has 200 or more requests between 2017-01-01.00:00:00 and 2017-01-01.00:59:59
192.168.247.138 has 200 or more requests between 2017-01-01.00:00:00 and 2017-01-01.00:59:59
[...]

```

If no access log file is specified then it will run the validation against the data already in the DB.

```
$ java -cp "parser.jar" com.ef.Parser --startDate=2017-01-01.00:00:00 --duration=hourly --threshold=200
Processing...
192.168.234.82 has 200 or more requests between 2017-01-01.00:00:00 and 2017-01-01.00:59:59
192.168.169.194 has 200 or more requests between 2017-01-01.00:00:00 and 2017-01-01.00:59:59
192.168.247.138 has 200 or more requests between 2017-01-01.00:00:00 and 2017-01-01.00:59:59
[...]

```

#### Other validations

 - Invalid access log file
 - Invalid start date (only formatting)
 - Invalid threshold 
 - Log format error (if a date is not in the expected format)

## About the code

This code tries to follow the guidelines suggested in the following books.

> Clean Code: A Handbook of Agile Software Craftsmanship (Robert C. Martin)

Gives guidelines to writing nice code that can be tested and extended with ease.

> Clean Architecture: A Craftsman's Guide to Software Structure and Design (Robert C. Martin Series)

This book introduces the concept of [clean architecture](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html).
In this program I skipped a few layers and stripped some boundaries, so if you are familiar with this concept please note that
this code is not a perfect example to follow.

Also, in order to organize my workflow I took advice from:

> Agile Estimating and Planning by Mike Cohn

And also:

> User Stories Applied by Mike Cohn

