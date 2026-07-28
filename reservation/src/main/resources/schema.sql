CREATE TABLE IF NOT EXISTS reservations (
                                            id UUID PRIMARY KEY,

                                            customer_id UUID NOT NULL,
                                            event_id UUID NOT NULL,
                                            session_id UUID NOT NULL,

                                            status VARCHAR(30) NOT NULL,

    total_amount NUMERIC(10,2) NOT NULL DEFAULT 0,

    expires_at TIMESTAMP,
    confirmed_at TIMESTAMP,
    cancelled_at TIMESTAMP,

    created_at TIMESTAMP,
    created_by VARCHAR(100),
    updated_at TIMESTAMP,
    updated_by VARCHAR(100)
    );

CREATE TABLE IF NOT EXISTS reservation_items (
                                                 id UUID PRIMARY KEY,

                                                 reservation_id UUID NOT NULL,

                                                 sector_id UUID NOT NULL,
                                                 seat_id UUID,

                                                 quantity INTEGER NOT NULL DEFAULT 1,

                                                 unit_price NUMERIC(10,2) NOT NULL DEFAULT 0,

    created_at TIMESTAMP,
    created_by VARCHAR(100),
    updated_at TIMESTAMP,
    updated_by VARCHAR(100),

    CONSTRAINT fk_reservation_item_reservation
    FOREIGN KEY (reservation_id)
    REFERENCES reservations(id)
    ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS shedlock (
                                        name VARCHAR(64) NOT NULL,
    lock_until TIMESTAMP NOT NULL,
    locked_at TIMESTAMP NOT NULL,
    locked_by VARCHAR(255) NOT NULL,
    PRIMARY KEY (name)
    );

CREATE INDEX IF NOT EXISTS idx_reservation_customer
    ON reservations(customer_id);

CREATE INDEX IF NOT EXISTS idx_reservation_session
    ON reservations(session_id);

CREATE INDEX IF NOT EXISTS idx_reservation_status
    ON reservations(status);

CREATE INDEX IF NOT EXISTS idx_reservation_expires
    ON reservations(expires_at);

CREATE INDEX IF NOT EXISTS idx_item_reservation
    ON reservation_items(reservation_id);

CREATE INDEX IF NOT EXISTS idx_item_sector
    ON reservation_items(sector_id);

CREATE INDEX IF NOT EXISTS idx_item_seat
    ON reservation_items(seat_id);