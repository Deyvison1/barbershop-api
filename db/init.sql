-- 🟦 Cria os bancos
CREATE DATABASE barbershop;
CREATE DATABASE keycloak;

-- 🟢 Conecta ao primeiro banco e cria o schema
\connect barbershop;
CREATE SCHEMA IF NOT EXISTS control_barbershop;

-- 🟢 Conecta ao segundo banco e cria o schema
\connect keycloak;
CREATE SCHEMA IF NOT EXISTS control_keycloak;

-- (Opcional) Mostra os schemas criados nos logs de inicialização
\dn;