CREATE TABLE matches (
    id UUID NOT NULL PRIMARY KEY,
    matchDate TIMESTAMP WITH TIME ZONE NOT NULL,
    findWords VARCHAR(255)
);