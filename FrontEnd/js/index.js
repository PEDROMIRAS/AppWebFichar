//Version 1.0
// Recoger los datos del formulario
const form = document.getElementById('form');
const nombre = document.getElementById('name');
const dni = document.getElementById('dni');
const contrasena = document.getElementById('password');
const contrasena2 = document.getElementById('password2');

// Validar los datos del formulario
function validarFormulario(e) {
    e.preventDefault();

    // Eliminar alertas previas
    const alertaExistente = document.querySelector('.alerta');
    if (alertaExistente) alertaExistente.remove();

    if (nombre.value.trim() === '' || dni.value.trim() === '' || contrasena.value.trim() === '' || contrasena2.value.trim() === '') {
        mostrarAlerta('Todos los campos son obligatorios', true);
        return;
    }

    if (contrasena.value.length < 6) {
        mostrarAlerta('La contraseña debe tener al menos 6 caracteres', true);
        return;
    }

    if (contrasena.value !== contrasena2.value) {
        mostrarAlerta('Las contraseñas no coinciden', true);
        return;
    }

    // Si todo está correcto, enviar los datos
    enviarDatos();
}

// Enviar datos del formulario a la API (simulación con console.log)
function enviarDatos() {
    const datos = {
        nombre: nombre.value.trim(),
        dni: dni.value.trim(),
        contrasena: contrasena.value.trim()
    };

    console.log('Datos enviados:', datos);
    mostrarAlerta('Registro completado correctamente');
}

// Mostrar alerta
function mostrarAlerta(mensaje, error = false) {
    const alerta = document.createElement('p');
    alerta.textContent = mensaje;
    alerta.classList.add('alerta', error ? 'error' : 'correcto');

    document.getElementById('form').appendChild(alerta);

    setTimeout(() => {
        alerta.remove();
    }, 3000);
}
// Boton iniciar sesion llevar a la pagina de inicio
function iniciarSesion() {
    window.location.href = "../html/inicio.html";
}

// Agregar evento al formulario en lugar del botón
form.addEventListener('submit', validarFormulario);

// Botón para iniciar sesión
document.getElementById('btn-login').addEventListener('click', iniciarSesion);