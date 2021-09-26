<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
        <title>Tienda Alem</title>
        <link rel="stylesheet" href="main.css" />
    </head>
    <body>
      
        <div class="upload-container">
            <div class="upload-header">
                <h2>Importando un CSV</h2>
            </div>
            <div class="upload-content">
                <div class="single-upload">
                    <h3>Suba el archivo </h3>
                    <form id="singleUploadForm" name="singleUploadForm">
                        <input id="singleFileUploadInput" type="file" name="file" class="file-input" required />
                        <button type="submit" class="primary submit-btn">Cargar</button>
                    </form>
                    <div class="upload-response">
                        <div id="singleFileUploadError"></div>
                        <div id="singleFileUploadSuccess"></div>
                    </div>
                </div>
               
            </div>
        </div>
        <script src="main.js" ></script>
    </body>
</html>