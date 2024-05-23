import lxml.etree as ET
import os

def transform_xml(xslt_path, xml_path, output_path):
    if os.path.exists(output_path):
        os.remove(output_path)
        print(f"{output_path} ha sido eliminado.")

    dom = ET.parse(xml_path)
    xslt = ET.parse(xslt_path)

    transform = ET.XSLT(xslt)

    newdom = transform(dom)

    with open(output_path, 'wb') as f:
        f.write(ET.tostring(newdom, pretty_print=True, encoding="UTF-8"))
        print(f"{output_path} ha sido creado.")

xml_file = 'EL_XSL/archivos/input.xml'
xslt_file = 'EL_XSL/archivos/transform.xsl'
output_file = 'EL_XSL/html/output.html'

transform_xml(xslt_file, xml_file, output_file)
