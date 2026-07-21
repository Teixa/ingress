CREATE TABLE venues (
                        id UUID PRIMARY KEY,

                        name VARCHAR(150) NOT NULL,
                        description VARCHAR(2000),

                        address VARCHAR(250) NOT NULL,
                        city VARCHAR(100) NOT NULL,
                        state VARCHAR(100) NOT NULL,
                        country VARCHAR(100) NOT NULL,
                        zip_code VARCHAR(20) NOT NULL,

                        latitude DOUBLE PRECISION NOT NULL,
                        longitude DOUBLE PRECISION NOT NULL,

                        active BOOLEAN NOT NULL,

                        created_at TIMESTAMP,
                        created_by VARCHAR(255),
                        updated_at TIMESTAMP,
                        updated_by VARCHAR(255)
);

CREATE TABLE sectors (
                         id UUID PRIMARY KEY,

                         venue_id UUID NOT NULL,

                         name VARCHAR(100) NOT NULL,

                         capacity INTEGER NOT NULL,

                         sector_type VARCHAR(30) NOT NULL,

                         created_at TIMESTAMP,
                         created_by VARCHAR(255),
                         updated_at TIMESTAMP,
                         updated_by VARCHAR(255),

                         CONSTRAINT fk_sector_venue
                             FOREIGN KEY (venue_id)
                                 REFERENCES venues(id)
);

CREATE TABLE seats (
                       id UUID PRIMARY KEY,

                       sector_id UUID NOT NULL,

                       seat_row VARCHAR(10) NOT NULL,

                       seat_number VARCHAR(10) NOT NULL,

                       seat_type VARCHAR(30) NOT NULL,

                       active BOOLEAN NOT NULL,

                       created_at TIMESTAMP,
                       created_by VARCHAR(255),
                       updated_at TIMESTAMP,
                       updated_by VARCHAR(255),

                       CONSTRAINT fk_seat_sector
                           FOREIGN KEY (sector_id)
                               REFERENCES sectors(id),

                       CONSTRAINT uk_sector_row_number
                           UNIQUE (sector_id, seat_row, seat_number)
);

CREATE INDEX idx_sector_venue
    ON sectors(venue_id);

CREATE INDEX idx_seat_sector
    ON seats(sector_id);