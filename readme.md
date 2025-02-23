# Trabalho de DCC168 - Testes de Software

## Parte 2

### Desenvolvedores
- Davi Kirchmaier 
- Mateus Alves

### Gerando Relatório de Cobertura de Testes

Execute os seguintes comandos no terminal:

```bash
# Gera o relatório
mvn clean test jacoco:report

# Navega até o diretório do relatório
cd target/site/jacoco

# Abre o relatório (Windows)
start index.html
```