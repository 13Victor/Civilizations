<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN" doctype-system="http://www.w3.org/TR/html4/loose.dtd" encoding="UTF-8"/>
    
    <xsl:template match="/root">
        <html lang="es">
            <head>
                <meta charset="UTF-8"/>
                <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
                <title>Units and Buildings</title>
                <link rel="stylesheet" type="text/css" href="../archivos/styles.css"/>
            </head>
            <body>
                <div class="container">
                    <div class="left-panel">
                        <!-- Ataque Units -->
                        <xsl:for-each select="attack_units/unit">
                            <div class="card" onclick="showInfo({position()})">
                                <img src="{sprite}" alt="{name}"/>
                                <h2><xsl:value-of select="name"/></h2>
                                <div class="type" style="background:#da7979">Attack Unit</div>
                            </div>
                        </xsl:for-each>
                        <!-- Defensa Units -->
                        <xsl:for-each select="defence_units/unit">
                            <div class="card" onclick="showInfo({position()})">
                                <img src="{sprite}" alt="{name}"/>
                                <h2><xsl:value-of select="name"/></h2>
                                <div class="type" style="background:#dad079">Defense Unit</div>
                            </div>
                        </xsl:for-each>
                        <!-- Especial Units -->
                        <xsl:for-each select="special_units/unit">
                            <div class="card" onclick="showInfo({position()})">
                                <img src="{sprite}" alt="{name}"/>
                                <h2><xsl:value-of select="name"/></h2>
                                <div class="type" style="background:#79b6da">Special Unit</div>
                            </div>
                        </xsl:for-each>
                        <!-- Buildings-->
                        <xsl:for-each select="buildings/building">
                            <div class="card" onclick="showInfo({position()})">
                                <img src="{sprite}" alt="{name}"/>
                                <h2><xsl:value-of select="name"/></h2>
                                <div class="type" style="background:#b379da">Building</div>
                            </div>
                        </xsl:for-each>
                    </div>
                    <div class="right-panel hidden" id="info-panel">
                        <h2 id="unit-name"></h2>
                        <p id="unit-damage"></p>
                        <p id="unit-armour"></p>
                        <p id="unit-waste-chance"></p>
                        <p id="unit-attack-again-chance"></p>
                    </div>
                </div>
                <script src="../archivos/script.js"></script>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
