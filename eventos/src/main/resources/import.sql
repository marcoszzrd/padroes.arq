-- 1. Categorias (5 registros)
INSERT INTO tb_categoria (nome, descricao) VALUES ('Tecnologia', 'Eventos e workshops focados em desenvolvimento e TI');
INSERT INTO tb_categoria (nome, descricao) VALUES ('Negócios', 'Palestras sobre empreendedorismo e gestão');
INSERT INTO tb_categoria (nome, descricao) VALUES ('Design', 'Encontros de UI/UX, artes e criação visual');
INSERT INTO tb_categoria (nome, descricao) VALUES ('Saúde', 'Simpósios e feiras sobre bem-estar e medicina');
INSERT INTO tb_categoria (nome, descricao) VALUES ('Educação', 'Seminários sobre métodos de ensino e inovação acadêmica');

-- 2. Locais (5 registros)
INSERT INTO tb_local (nome, endereco, capacidade) VALUES ('Auditório Central', 'Av. Paulista, 1000 - São Paulo, SP', 500);
INSERT INTO tb_local (nome, endereco, capacidade) VALUES ('Centro de Convenções', 'Rua das Flores, 500 - Curitiba, PR', 1200);
INSERT INTO tb_local (nome, endereco, capacidade) VALUES ('Espaço Inovação', 'Av. Rio Branco, 200 - Rio de Janeiro, RJ', 300);
INSERT INTO tb_local (nome, endereco, capacidade) VALUES ('Teatro Municipal', 'Praça da Sé, 50 - Salvador, BA', 800);
INSERT INTO tb_local (nome, endereco, capacidade) VALUES ('Sala de Treinamento A', 'Rua XV de Novembro, 120 - Campinas, SP', 100);

-- 3. Palestrantes (5 registros)
INSERT INTO tb_palestrante (nome, mini_bio, email) VALUES ('Carlos Silva', 'Especialista em Inteligência Artificial e Ciência de Dados', 'carlos.silva@email.com');
INSERT INTO tb_palestrante (nome, mini_bio, email) VALUES ('Mariana Oliveira', 'Consultora de Marketing Digital e Growth Hacking', 'mariana.oliveira@email.com');
INSERT INTO tb_palestrante (nome, mini_bio, email) VALUES ('Roberto Santos', 'Arquiteto de Software Java e Cloud Solutions', 'roberto.santos@email.com');
INSERT INTO tb_palestrante (nome, mini_bio, email) VALUES ('Fernanda Lima', 'Designer Leader com foco em Design Systems', 'fernanda.lima@email.com');
INSERT INTO tb_palestrante (nome, mini_bio, email) VALUES ('Lucas Mendes', 'Doutor em Educação e Tecnologia Educacional', 'lucas.mendes@email.com');

-- 4. Participantes (5 registros)
INSERT INTO tb_participante (nome, email, telefone) VALUES ('Ana Souza', 'ana.souza@email.com', '(11) 98765-4321');
INSERT INTO tb_participante (nome, email, telefone) VALUES ('Bruno Costa', 'bruno.costa@email.com', '(21) 99876-5432');
INSERT INTO tb_participante (nome, email, telefone) VALUES ('Camila Rocha', 'camila.rocha@email.com', '(41) 97654-3210');
INSERT INTO tb_participante (nome, email, telefone) VALUES ('Diego Martins', 'diego.martins@email.com', '(71) 96543-2109');
INSERT INTO tb_participante (nome, email, telefone) VALUES ('Elena Ferreira', 'elena.ferreira@email.com', '(19) 95432-1098');

-- 5. Eventos (5 registros)
INSERT INTO tb_evento (nome, descricao, data_inicio, data_fim, capacidade, status, categoria_id, local_id, palestrante_id) VALUES ('Summit de Java 2026', 'Imersão em Java 21, Spring Boot 3 e Arquitetura', '2026-10-10T09:00:00Z', '2026-10-10T18:00:00Z', 300, 'CONFIRMADO', 1, 1, 3);
INSERT INTO tb_evento (nome, descricao, data_inicio, data_fim, capacidade, status, categoria_id, local_id, palestrante_id) VALUES ('Feira de Empreendedorismo', 'Estratégias de vendas e captação de clientes', '2026-11-05T10:00:00Z', '2026-11-05T17:00:00Z', 500, 'CONFIRMADO', 2, 2, 2);
INSERT INTO tb_evento (nome, descricao, data_inicio, data_fim, capacidade, status, categoria_id, local_id, palestrante_id) VALUES ('Workshop UX/UI Design', 'Práticas modernas de prototipagem e testes', '2026-11-20T13:00:00Z', '2026-11-20T19:00:00Z', 100, 'ABERTO', 3, 3, 4);
INSERT INTO tb_evento (nome, descricao, data_inicio, data_fim, capacidade, status, categoria_id, local_id, palestrante_id) VALUES ('Congresso de Saúde Mental', 'Abordagens contemporâneas e bem-estar corporativo', '2026-12-01T08:00:00Z', '2026-12-02T17:00:00Z', 400, 'CONFIRMADO', 4, 4, 1);
INSERT INTO tb_evento (nome, descricao, data_inicio, data_fim, capacidade, status, categoria_id, local_id, palestrante_id) VALUES ('Simpósio EdTech', 'O futuro da educação mediada por tecnologia', '2026-12-10T14:00:00Z', '2026-12-10T21:00:00Z', 80, 'ABERTO', 5, 5, 5);

-- 6. Inscrições (5 registros)
INSERT INTO tb_inscricao (data_inscricao, status, evento_id, participante_id) VALUES ('2026-09-01T10:00:00Z', 'APROVADA', 1, 1);
INSERT INTO tb_inscricao (data_inscricao, status, evento_id, participante_id) VALUES ('2026-09-02T11:30:00Z', 'APROVADA', 1, 2);
INSERT INTO tb_inscricao (data_inscricao, status, evento_id, participante_id) VALUES ('2026-09-03T14:15:00Z', 'PENDENTE', 2, 3);
INSERT INTO tb_inscricao (data_inscricao, status, evento_id, participante_id) VALUES ('2026-09-04T09:00:00Z', 'APROVADA', 3, 4);
INSERT INTO tb_inscricao (data_inscricao, status, evento_id, participante_id) VALUES ('2026-09-04T16:45:00Z', 'CANCELADA', 5, 5);