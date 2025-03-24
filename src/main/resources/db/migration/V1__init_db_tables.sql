CREATE TABLE bank (
    id SERIAL PRIMARY KEY,
    iso2 VARCHAR(2) NOT NULL,
    swift VARCHAR(11) NOT NULL UNIQUE,
    code_type VARCHAR(10),
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    city VARCHAR(50),
    country VARCHAR(50) NOT NULL,
    timezone VARCHAR(50),
    is_headquarter BOOLEAN NOT NULL
);

CREATE TABLE headquarter_branch (
    headquarter_id INTEGER,
    branch_id INTEGER,
    PRIMARY KEY (headquarter_id, branch_id),
    FOREIGN KEY (headquarter_id) REFERENCES bank(id) ON DELETE CASCADE,
    FOREIGN KEY (branch_id) REFERENCES bank(id) ON DELETE CASCADE
);