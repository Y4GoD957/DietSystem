<template>
  <footer>
    <div class="footer-section">
      <h3>Central de Atendimento</h3>
      <ul>
        <li>
          <p><ion-icon name="time-outline"></ion-icon> Atendimento: seg. à dom. 07 às 23h</p>
        </li>
        <li>
          <p><ion-icon name="logo-whatsapp"></ion-icon> Contato: +55 (61) 98190-5006</p>
        </li>
        <li>
          <p><ion-icon name="mail-outline"></ion-icon> Email: contato@dietsystem.com</p>
        </li>
      </ul>
    </div>

    <div class="footer-section services-section">
      <h3>Serviços ao Cliente</h3>
      <nav>
        <ul>
          <li>
            <a class="header-and-footer" href="/Services/TMBCalculate"
              >Cálculo de Metabolismo Basal</a
            >
          </li>
          <li>
            <a class="header-and-footer" href="/Services/MealsPage">Formulador de Dietas</a>
          </li>
        </ul>
      </nav>
    </div>

    <div class="footer-section">
      <h3>Informações e Políticas</h3>
      <nav>
        <ul>
          <li><a class="header-and-footer" href="/AboutUs">Sobre Nós</a></li>
          <li><a class="header-and-footer" href="/terms">Termos e Serviços</a></li>
          <li><a class="header-and-footer" href="/privacy-policy">Política de Privacidade</a></li>
        </ul>
      </nav>
    </div>
  </footer>
</template>

<script>
export default {
  name: 'TheFooter'
}
</script>

<style scoped>
h3 {
  font-weight: bold;
}

.footer-section {
  margin: 10px;
  flex: 1;
  min-width: 200px;
}

.services-section h3 {
  margin-left: 40px;
}

.footer-section h3 {
  font-size: 1.5em;
  margin-bottom: 15px;
}

.footer-section ul {
  list-style: none;
  padding: 0;
}

.footer-section ul li {
  margin-bottom: 10px;
  font-weight: bold; /* Itens em negrito */
}
</style>
