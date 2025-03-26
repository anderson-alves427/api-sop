CREATE TABLE despesa (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    numero_protocolo VARCHAR(255) NOT NULL UNIQUE,
    tipo_despesa VARCHAR(50) NOT NULL CHECK (tipo_despesa IN ('OBRA_EDIFICACAO', 'OBRA_RODOVIAS', 'OUTROS')),
    data_protocolo TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_vencimento DATE NOT NULL,
    credor VARCHAR(255) NOT NULL,
    descricao TEXT NOT NULL,
    valor NUMERIC(15,2) NOT NULL CHECK (valor >= 0.01),
    status VARCHAR(50) NOT NULL DEFAULT 'AGUARDANDO_EMPENHO' CHECK (status IN ('AGUARDANDO_EMPENHO', 'PARCIALMENTE_EMPENHADA', 'AGUARDANDO_PAGAMENTO', 'PARCIALMENTE_PAGA', 'PAGA'))
);

CREATE UNIQUE INDEX unique_numero_protocolo ON despesa (numero_protocolo);

INSERT INTO despesa (
    id, numero_protocolo, tipo_despesa, data_protocolo, data_vencimento, credor, descricao, valor, status
) VALUES (
    gen_random_uuid(), '11111.000001/2025-03', 'OBRA_EDIFICACAO', NOW(), '2025-04-15', 'Construtora XYZ', 'Construção de escola', 150000.50, 'AGUARDANDO_EMPENHO'
);



CREATE TABLE empenho (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    numero_empenho VARCHAR(12) NOT NULL UNIQUE CHECK (numero_empenho ~ '^[0-9]{4}NE[0-9]{4}$'),
    data_empenho DATE NOT NULL,
    valor_empenho NUMERIC(15,2) NOT NULL,
    observacao VARCHAR(500),
    despesa_id UUID NOT NULL,
    CONSTRAINT fk_empenho_despesa FOREIGN KEY (despesa_id) REFERENCES despesa (id) ON DELETE RESTRICT
);

CREATE UNIQUE INDEX unique_numero_empenho ON empenho (numero_empenho);



CREATE TABLE pagamento (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    numero_pagamento VARCHAR(12) NOT NULL UNIQUE CHECK (numero_pagamento ~ '^[0-9]{4}NP[0-9]{4}$'),
    data_pagamento DATE NOT NULL,
    valor_pagamento NUMERIC(15,2) NOT NULL,
    observacao VARCHAR(500),
    empenho_id UUID NOT NULL,
    CONSTRAINT fk_pagamento_empenho FOREIGN KEY (empenho_id) REFERENCES empenho (id) ON DELETE RESTRICT
);

CREATE UNIQUE INDEX unique_numero_pagamento ON pagamento (numero_pagamento);