//Variables de informacion de inicio de sesion
const dni = document.getElementById('dni');
const contrasena = document.getElementById('password');

//Boton de iniciar sesion
const iniciarSesion = document.getElementById('btn-form');

//Evento de click en el boton de iniciar sesion
iniciarSesion.addEventListener('click', validarInicioSesion);

//Validar los datos del formulario que existen en mi base de datos
function validarInicioSesion(e) {
    e.preventDefault();

    //Eliminar alertas previas
    const alertaExistente = document.querySelector('.alerta');
    if (alertaExistente) alertaExistente.remove();

    if (dni.value.trim() === '' || contrasena.value.trim() === '') {
        mostrarAlerta('Todos los campos son obligatorios', true);
        return;
    }

    // Si todo está correcto, enviar los datos
    enviarDatos();
}
//Enviar datos del formulario a la API (simulación con console.log)
function enviarDatos() {
    const datos = {
        dni: dni.value.trim(),
        contrasena: contrasena.value.trim()
    };
    console.log('Datos enviados:', datos);
    mostrarAlerta('Inicio de sesión correcto');
}

//Mostrar alerta
function mostrarAlerta(mensaje, error = false) {
    const alerta = document.createElement('p');
    alerta.textContent = mensaje;
    alerta.classList.add('alerta', error ? 'error' : 'correcto');

    document.getElementById('form').appendChild(alerta);

    setTimeout(() => {
        alerta.remove();
    }, 3000);
}
