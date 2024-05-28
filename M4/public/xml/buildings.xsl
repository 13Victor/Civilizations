<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN" doctype-system="http://www.w3.org/TR/html4/loose.dtd" encoding="UTF-8"/>
    
    <xsl:template match="/buildings">
        <html lang="es">
            <head>
                <meta charset="UTF-8"/>
                <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
                <title>Buildings</title>
                <link rel="stylesheet" type="text/css" href="../../css/wiki_units.css"/>
            </head>
            <body>
                <div class="title tpurple">
                    <h1>Buildings</h1>
                </div>
                <div class="cards-container" style="padding-left: 300px; padding-right: 300px;">
                    <!-- Buildings -->
                    <xsl:for-each select="building">
                        <div class="flip" style="height: 322px">
                            <div class="front purple" style="border: 10px solid #624180;">
                                <img src="{sprite}" alt="{name}" style="filter: drop-shadow(7px 5px 2px rgb(0, 0, 0,0.6))"/>
                                <h2 style="padding: 0px"><xsl:value-of select="name"/></h2>
                                <div class="type" style="background:#b379da; border: 3px solid #624180;">Building</div>
                            </div>
                            <div class="back purple" style="border: 10px solid #624180;">
                                <h2><xsl:value-of select="name"/></h2>
                                <div class="stats" style="background:#e2c051; border: 3px solid #77652b;">
                                    <h3>Description</h3>
                                    <div class="statpackage">
                                        <div class="stat" style="padding: 0px 10px 0px 10px; justify-content: center; align-items: center; display: flex; height: 60px; background-color: #ad933e; border: 3px solid #77652b;">
                                            <xsl:choose>
                                <xsl:when test="name = 'Farm'">
                                    <div>
                                        <p>For each farm built, increase your food production by 10%.</p>
                                    </div>
                                </xsl:when>
                                <xsl:when test="name = 'Smithy'">
                                    <div>
                                        <p>For each smithy built, increase your iron production by 10%.</p>
                                    </div>
                                </xsl:when>
                                <xsl:when test="name = 'Carpentry'">
                                    <div>
                                        <p>For each carpentry built, increase your wood production by 10%</p>
                                    </div>
                                </xsl:when>
                                <xsl:when test="name = 'Magictower'">
                                    <div>
                                        <p>For each magic tower built, get 3000 mana every 30 seconds.</p>
                                    </div>
                                </xsl:when>
                                <xsl:when test="name = 'Church'">
                                    <div>
                                        <p>For every church built, we will be able to generate 1 priest.</p>
                                    </div>
                                </xsl:when>
                                <xsl:otherwise>
                                    <div>
                                        <p>Additional details about the building...</p>
                                    </div>
                                </xsl:otherwise>
                            </xsl:choose>
                                        </div>
                                    </div>
                                </div>
                                <div class="table2">
                                    <h3>Costs</h3>
                                    <table style="">
                                        <thead>
                                            <tr>
                                                <th style="width: 25%; border: 3px solid #624180;"><img src="../../resources/img/steak.png" alt="steak"/></th>
                                                <th style="width: 25%"><img src="../../resources/img/wood.png" alt="wood"/></th>
                                                <th style="width: 25%"><img src="../../resources/img/iron.png" alt="iron"/></th>
                                                <th style="width: 25%"><img src="../../resources/img/mana.png" alt="mana"/></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td style="text-align: center; color: #fff; background-color: #8e5ab1;"><xsl:value-of select="costs/food_cost"/></td>
                                                <td style="background-color: #8e5ab1;"><xsl:value-of select="costs/wood_cost"/></td>
                                                <td style="background-color: #8e5ab1;"><xsl:value-of select="costs/iron_cost"/></td>
                                                <td style="background-color: #8e5ab1;"><xsl:value-of select="costs/mana_cost"/></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </xsl:for-each>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
