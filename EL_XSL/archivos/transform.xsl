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
                <div class="cards-container">
                    <!-- Ataque Units -->
                    <xsl:for-each select="attack_units/unit">
                        <div class="flip">
                            <div class="front">
                                <img src="{sprite}" alt="{name}" style="filter: drop-shadow(7px 5px 2px rgb(218, 121, 121, 0.6))"/>
                                <h2><xsl:value-of select="name"/></h2>
                                <div class="type" style="background:#da7979;">Attack Unit</div>
                            </div>
                            <div class="back">
                                <h2><xsl:value-of select="name"/></h2>
                                <p>Additional details about the unit...</p>
                            </div>
                        </div>
                    </xsl:for-each>
                    <!-- Defensa Units -->
                    <xsl:for-each select="defence_units/unit">
                        <div class="flip">
                            <div class="front">
                                <img src="{sprite}" alt="{name}" style="filter: drop-shadow(7px 5px 2px rgb(218, 208, 121,0.6))"/>
                                <h2><xsl:value-of select="name"/></h2>
                                <div class="type" style="background:#dad079">Defense Unit</div>
                            </div>
                            <div class="back">
                                <h2><xsl:value-of select="name"/></h2>
                                <p>Additional details about the unit...</p>
                            </div>
                        </div>
                    </xsl:for-each>
                    <!-- Especial Units -->
                    <xsl:for-each select="special_units/unit">
                        <div class="flip">
                            <div class="front">
                                <img src="{sprite}" alt="{name}" style="filter: drop-shadow(7px 5px 2px rgb(121, 182, 218,0.6))"/>
                                <h2><xsl:value-of select="name"/></h2>
                                <div class="type" style="background:#79b6da">Special Unit</div>
                            </div>
                            <div class="back">
                                <h2><xsl:value-of select="name"/></h2>
                                <p>Additional details about the unit...</p>
                            </div>
                        </div>
                    </xsl:for-each>
                    <!-- Buildings -->
                    <xsl:for-each select="buildings/building">
                        <div class="flip">
                            <div class="front">
                                <img src="{sprite}" alt="{name}" style="filter: drop-shadow(7px 5px 2px rgb(179, 121, 218, 0.6))"/>
                                <h2><xsl:value-of select="name"/></h2>
                                <div class="type" style="background:#b379da">Building</div>
                            </div>
                            <div class="back">
                                <h2><xsl:value-of select="name"/></h2>
                                <p>Additional details about the building...</p>
                            </div>
                        </div>
                    </xsl:for-each>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
