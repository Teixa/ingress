CREATE TABLE company_organizers (
                                    id UUID PRIMARY KEY,

                                    company_name VARCHAR(255) NOT NULL,
                                    cnpj VARCHAR(255) NOT NULL UNIQUE,
                                    email VARCHAR(255) NOT NULL,
                                    phone_number VARCHAR(255),

                                    created_at TIMESTAMP NOT NULL,
                                    created_by VARCHAR(100) NOT NULL,
                                    updated_at TIMESTAMP,
                                    updated_by VARCHAR(100)
);

CREATE TABLE events (
                        id UUID PRIMARY KEY,

                        name VARCHAR(150) NOT NULL,
                        description VARCHAR(3000) NOT NULL,

                        image_url VARCHAR(500),
                        banner_url VARCHAR(500),

                        minimum_age INTEGER NOT NULL,

                        event_category VARCHAR(255) NOT NULL,

                        company_organizer_id UUID NOT NULL,

                        venue_id UUID NOT NULL,

                        status VARCHAR(255) NOT NULL,

                        created_at TIMESTAMP NOT NULL,
                        created_by VARCHAR(100) NOT NULL,
                        updated_at TIMESTAMP,
                        updated_by VARCHAR(100),

                        CONSTRAINT fk_event_company_organizer
                            FOREIGN KEY (company_organizer_id)
                                REFERENCES company_organizers(id)
);

CREATE TABLE sessions (
                         id UUID PRIMARY KEY,

                         event_id UUID NOT NULL,

                         date_time TIMESTAMP NOT NULL,

                         sales_start TIMESTAMP NOT NULL,

                         sales_end TIMESTAMP NOT NULL,

                         duration BIGINT,

                         status VARCHAR(255) NOT NULL,

                         notes VARCHAR(500),

                         gates_open_at TIMESTAMP,

                         created_at TIMESTAMP NOT NULL,
                         created_by VARCHAR(100) NOT NULL,
                         updated_at TIMESTAMP,
                         updated_by VARCHAR(100),

                         CONSTRAINT fk_session_event
                             FOREIGN KEY (event_id)
                                 REFERENCES events(id)
);

-- CREATE INDEX idx_events_company_organizer
--     ON events(company_organizer_id);
--
-- CREATE INDEX idx_events_venue
--     ON events(venue_id);
--
-- CREATE INDEX idx_session_event
--     ON session(event_id);
--
-- CREATE INDEX idx_session_date
--     ON session(date_time);