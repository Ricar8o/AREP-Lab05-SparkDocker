# AREP - Laboratorio #5



## Comenzando 
Para obtener una copia local del repositorio puede ejecutar la siguiente línea en la consola de comandos.
    
    git clone https://github.com/Ricar8o/AREP-Lab05-SparkDocker.git

## Pre-requisitos

Debe tener instalado lo siguiente:

* [GIT](https://git-scm.com/book/es/v2/Inicio---Sobre-el-Control-de-Versiones-Instalación-de-Git)
* [JAVA 8](https://www.java.com/es/download/)
* [MAVEN](https://maven.apache.org)

GIT no es completamente necesario pero si es recomendable, también puede descargar el repositorio como un .zip.

## Otras Tecnologías
* [MongoDB](www.mongodb.com) - MongoDB es una base de datos de documentos que ofrece una gran escalabilidad y flexibilidad, y un modelo de consultas e indexación avanzado.
* [CircleCI](https://circleci.com/) - Es una plataforma que ofrece integración continua en el codigo.

## Pruebas y Compilación

Para compilar el proyecto podemos ejecutar varias opciones. 

* Para compilar el codigo fuente únicamente.

        mvn compile

* Para ejecutar todas las fases.

        mvn package

## Ejecución
Para ejecutar el programa puede hacerlo desle la linea de comandos ejecutando el siguiente comando.



### NOTAS: 



 
## Desplegando de manera local



### Inicio 


![connect](img/connect.jpg)

### Obteniendo archivos
HTML

![Prueba1](img/prueba1.jpg)

CSS

![Prueba2](img/prueba2.jpg)

JS 

![Prueba3](img/prueba3.jpg)

PNG

![Prueba4](img/prueba4.jpg)


## Despliegue Circleci

[![CircleCI](https://circleci.com/gh/Ricar8o/AREP-Lab03-Servidor.svg?style=svg)](https://app.circleci.com/pipelines/github/Ricar8o/AREP-Lab05-SparkDocker)

## Documentación

La documentacion se encuentra en la carpeta del repositorio llamada [docs](docs), pero en caso de que quiera generarla tiene las siguientes opciones.

### Maven
Para generar la documentacion con Maven puede ejecutar cualquiera de los siguientes comandos.

*        mvn javadoc:javadoc
*        mvn javadoc:jar


### Java
Si quiere generarla usando java puede hacerlo con el siguiente comando.

*       javadoc -d docs src\main\java\co\edu\escuelaing\arep\sparkr\*.java src\main\java\co\edu\escuelaing\arep\sparkr\httpserver\*.java

## PDF Diseño

[Arep_Lab_5.PDF](Arep_Lab_5.pdf)



## Autor 

Andrés Ricardo Martínez Díaz - [Ricar8o](https://github.com/Ricar8o)

## Licencia
Mire el archivo [LICENSE](LICENSE) para más detalles.
