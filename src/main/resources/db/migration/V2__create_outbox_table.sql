CREATE TABLE IF NOT EXISTS outbox_messages (
    id varchar PRIMARY KEY,
    aggregate_type varchar(255) NOT NULL,
    aggregate_id varchar(255) NOT NULL,
    event_type varchar(255) NOT NULL,
    routing_key varchar(255) NOT NULL,
    payload text NOT NULL,
    created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    processed_at timestamp DEFAULT NULL
);

CREATE INDEX idx_outbox_unprocessed ON outbox_messages (processed_at) WHERE processed_at IS NULL;
CREATE INDEX idx_outbox_aggregate ON outbox_messages (aggregate_type, aggregate_id);