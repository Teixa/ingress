-- Note: Removed Postgres-specific extension and defaults to keep schema portable for H2 tests.
-- For Postgres production you can enable uuid-ossp and set defaults (uuid_generate_v4()).

CREATE TABLE IF NOT EXISTS reservation (
    id uuid PRIMARY KEY,
    customer_id uuid NOT NULL,
    status varchar(20) NOT NULL,
    total_amount numeric(12,2) DEFAULT 0,
    created_at timestamp without time zone DEFAULT now(),
    expires_at timestamp without time zone,
    created_by varchar(100),
    updated_at timestamp without time zone,
    updated_by varchar(100)
);

CREATE TABLE IF NOT EXISTS reservation_item (
    id uuid PRIMARY KEY,
    reservation_id uuid NOT NULL REFERENCES reservation(id) ON DELETE CASCADE,
    event_id uuid NOT NULL,
    session_id uuid NOT NULL,
    seat_id uuid,
    price numeric(12,2) NOT NULL,
    quantity integer NOT NULL DEFAULT 1
);

CREATE TABLE IF NOT EXISTS shedlock (
    name varchar(64) NOT NULL,
    lock_until timestamp NOT NULL,
    locked_at timestamp NOT NULL,
    locked_by varchar(255) NOT NULL,
    PRIMARY KEY (name)
);

CREATE INDEX IF NOT EXISTS idx_reservation_customer ON reservation(customer_id);
CREATE INDEX IF NOT EXISTS idx_reservation_status_expires ON reservation(status, expires_at);

-- If you prefer DB-generated UUIDs in Postgres, run:
-- CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
-- and set column defaults to uuid_generate_v4().


