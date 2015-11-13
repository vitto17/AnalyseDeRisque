#! /bin/bash

java -cp lib/hsqldb-2.3.1.jar org.hsqldb.server.Server -database.0 file:formation_orion_db_files/dbdemo -dbname.0 demo
