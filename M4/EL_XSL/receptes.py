import lxml.etree as ET
import os

def transform_xml(xslt_path, xml_path, output_path):
    # Borrar el archivo HTML existente si existe
    if os.path.exists(output_path):
        os.remove(output_path)
        print(f"{output_path} ha sido eliminado.")

    # Parsear los archivos XML y XSLT
    dom = ET.parse(xml_path)
    xslt = ET.parse(xslt_path)

    # Crear la transformación
    transform = ET.XSLT(xslt)

    # Aplicar la transformación al XML
    newdom = transform(dom)

    # Escribir la salida en un archivo HTML
    with open(output_path, 'wb') as f:
        f.write(ET.tostring(newdom, pretty_print=True, encoding="UTF-8"))
        print(f"{output_path} ha sido creado.")

# Rutas de los archivos
xml_file = 'M4/EL_XSL/xml/xml.xml'
xslt_file = 'M4/EL_XSL/xml/wiki.xsl'
output_file = 'M4/EL_XSL/html/output.html'

# Ejecutar la transformación
transform_xml(xslt_file, xml_file, output_file)
