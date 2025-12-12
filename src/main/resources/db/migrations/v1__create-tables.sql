CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE statusTask(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    createdAt DATE,
    updated DATE,
    deleted BOOLEAN
);
