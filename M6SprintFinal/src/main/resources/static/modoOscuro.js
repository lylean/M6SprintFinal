/**
 * 
 */// script.js

// Función para cambiar los colores a modo oscuro
function cambiarModoOscuro() {
  // Cambiar los colores del navbar
  document.querySelector('.navbar').style.backgroundColor = '#333333';
  document.querySelectorAll('.navbar-link').forEach(link => {
    link.style.color = '#fff';
  });

  // Cambiar los colores del footer
  document.querySelector('.site-footer').style.backgroundColor = '#333333';
  document.querySelector('.site-footer').style.color = '#fff';

  // Cambiar el color de fondo del body
  document.body.style.backgroundColor = '#625F5F';
}

// Función para restaurar los colores a modo luz
function cambiarModoLuz() {
  // Restaurar los colores originales del navbar
  document.querySelector('.navbar').style.backgroundColor = '#4267B2';
  document.querySelectorAll('.navbar-link').forEach(link => {
    link.style.color = 'white';
  });

  // Restaurar los colores originales del footer
  document.querySelector('.site-footer').style.backgroundColor = '#4267B2';
  document.querySelector('.site-footer').style.color = '#fff';

  // Restaurar el color de fondo original del body
  document.body.style.backgroundColor = '#fff';
}

// Event listener para detectar cuando se selecciona una opción del dropdown
document.querySelectorAll('.dropdown-item').forEach(item => {
  item.addEventListener('click', function() {
    if (this.textContent === 'Luz') {
      cambiarModoLuz();
    } else if (this.textContent === 'Oscuro') {
      cambiarModoOscuro();
    }
  });
});
