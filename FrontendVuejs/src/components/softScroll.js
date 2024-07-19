export default{
  mounted() {
    document.addEventListener("DOMContentLoaded", function() {
      const links = document.querySelectorAll('a[href^="#"]');
    
      links.forEach(link => {
        link.addEventListener("click", function(event) {
          event.preventDefault();
    
          const targetClass = this.getAttribute("href").substring(1);
    
          // Check if we are on the main page
          if (window.location.pathname === '/') {
            scrollToSection(targetClass);
          } else {
            // Navigate to the main page and then scroll to the section
            window.sessionStorage.setItem('scrollTo', targetClass);
            window.location.href = '/';
          }
        });
      });
    
      // Scroll to section if specified in sessionStorage
      const scrollTo = window.sessionStorage.getItem('scrollTo');
      if (scrollTo) {
        window.sessionStorage.removeItem('scrollTo');
        scrollToSection(scrollTo);
      } else {
        window.scrollTo(0, 0); // Force scroll to the top on initial load
      }
    });
    
    function scrollToSection(targetClass) {
      const targetElement = document.querySelector(`.section-${targetClass}`);
    
      if (targetElement) {
        // Defina valores de deslocamento específicos para cada seção
        let offset = 0;
        if (targetClass === 'main') {
          offset = 200;
        } else if (targetClass === 'about') {
          offset = 100;
        } else if (targetClass === 'services') {
          offset = 120;
        } else if (targetClass === 'contact') {
          offset = -200;
        }
    
        const elementPosition = targetElement.getBoundingClientRect().top + window.scrollY;
        const offsetPosition = elementPosition - offset;
    
        window.scrollTo({
          top: offsetPosition,
          behavior: "smooth"
        });
      }
    }          
  }
}
