#!/bin/bash
export PGPASSWORD='postgres1'

psql -U postgres -f "./createdb.sql" &&
psql -U postgres -d campground -f "./campground.sql"
