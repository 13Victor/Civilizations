from lxml import etree
import os

# Leer un archivo XML
def read_xml(path):
    with open(path, 'r', encoding='utf-8') as file:
        string = file.read()
    return bytes(bytearray(string, encoding='utf-8'))

# Escribir un archivo HTML
def write_html(path, html):
    with open(path, 'w', encoding='utf-8') as file:
        file.write(html)

# Transformar un XML a HTML usando un XSL específico
def transform_xml_to_html(xml_path, xsl_path, output_path):
    try:
        # Leer el archivo XML y XSL
        xml_data = read_xml(xml_path)
        xsl_data = read_xml(xsl_path)

        # Parsear los archivos XML y XSL
        xml_tree = etree.XML(xml_data)
        xsl_tree = etree.XML(xsl_data)

        # Crear el transformador XSLT
        transform = etree.XSLT(xsl_tree)
        html_dom = transform(xml_tree)
        
        # Verificar si la transformación fue exitosa
        if html_dom is None:
            raise ValueError(f"Transformation resulted in None for XML: {xml_path} with XSL: {xsl_path}")

        # Convertir el resultado a una cadena HTML
        html_result = etree.tostring(html_dom, pretty_print=True).decode('utf-8')
        
        # Escribir el HTML resultante al archivo de salida
        write_html(output_path, html_result)
    except Exception as e:
        print(f"Error transforming {xml_path} using {xsl_path}: {e}")

# Rutas de archivos XML y XSL
xml_files = ['EL_XSL_2/xml/attack_units.xml', 'EL_XSL_2/xml/buildings.xml', 'EL_XSL_2/xml/defence_units.xml', 'EL_XSL_2/xml/special_units.xml']
xsl_files = ['EL_XSL_2/xml/attack_units.xsl', 'EL_XSL_2/xml/buildings.xsl', 'EL_XSL_2/xml/defence_units.xsl', 'EL_XSL_2/xml/special_units.xsl']
output_files = ['EL_XSL_2/html/attack_units.html', 'EL_XSL_2/html/buildings.html', 'EL_XSL_2/html/defence_units.html', 'EL_XSL_2/html/special_units.html']

# Limpiar el directorio de HTML
def clean_html_directory(directory):
    for file in os.listdir(directory):
        if file.endswith(".html"):
            os.remove(os.path.join(directory, file))

# Limpiar el directorio de HTML
clean_html_directory('EL_XSL_2/html')

# Transformar cada archivo XML usando su correspondiente XSL y guardar el resultado en un archivo HTML
for xml_path, xsl_path, output_path in zip(xml_files, xsl_files, output_files):
    transform_xml_to_html(xml_path, xsl_path, output_path)
