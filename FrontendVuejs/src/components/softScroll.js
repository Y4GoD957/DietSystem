export default {
    mounted() {
      document.addEventListener("DOMContentLoaded", function() {
        const links = document.querySelectorAll('a[href^="#"]');
      
        links.forEach(link => {
          link.addEventListener("click", function(event) {
            event.preventDefault();
      
            const targetId = this.getAttribute("href").substring(1);
            const targetElement = document.getElementById(targetId);
            
            if (targetElement) {
              // Defina valores de deslocamento específicos para cada seção
              let offset = 0;
              if (targetId === 'main') {
                offset = 200;
              } else if (targetId === 'about') {
                offset = 100;
              } else if (targetId === 'services') {
                offset = 120;
              } else if (targetId === 'contact') {
                offset = -200;
              }
      
              const elementPosition = targetElement.getBoundingClientRect().top + window.pageYOffset;
              const offsetPosition = elementPosition - offset;
      
              window.scrollTo({
                top: offsetPosition,
                behavior: "smooth"
              });
            }
          });
        });
      });
}}
