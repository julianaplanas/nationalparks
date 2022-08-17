-- this script CANNOT be run while connected to vendingmachine
-- best practice is to run as admin on the postgres database

-- The following line terminates any active connections to the database so that it can be dropped
SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE datname = 'campground';

DROP DATABASE IF EXISTS campground;

CREATE DATABASE campground;
