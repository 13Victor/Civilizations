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

# Combinar varios árboles XML en uno solo
def combine_xml(xml_files):
    combined_root = etree.Element("root")
    for xml_file in xml_files:
        xml_content = read_xml(xml_file)
        xml_tree = etree.XML(xml_content)
        combined_root.extend(xml_tree)
    return etree.ElementTree(combined_root)

# Transformar un árbol XML combinado a HTML
def transform_combined_xml(xmlTree, xsl_path, output_path):
    xsl_content = read_xml(xsl_path)
    xsl_tree = etree.XML(xsl_content)
    transform = etree.XSLT(xsl_tree)
    html_dom = transform(xmlTree)
    html_result = etree.tostring(html_dom, pretty_print=True).decode('utf-8')
    write_html(output_path, html_result)

# Lista de archivos XML (verifica que las rutas sean correctas)
xml_files = [
    './xml/attack_units.xml',
    './xml/defences.xml',
    './xml/buildings.xml',
    './xml/special_units.xml'
]

# Combinar los archivos XML en uno solo
combined_xml_tree = combine_xml(xml_files)

# Transformar el XML combinado y guardar el resultado en un archivo HTML
transform_combined_xml(combined_xml_tree, './xml/template.xsl', './html/output.html')
