<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN" doctype-system="http://www.w3.org/TR/html4/loose.dtd" encoding="UTF-8"/>
    
    <xsl:template match="/special_units">
        <html lang="es">
            <head>
                <meta charset="UTF-8"/>
                <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
                <title>Special Units</title>
                <link rel="stylesheet" type="text/css" href="../../css/wiki_units.css"/>
            </head>
            <body>
                <div class="title tblue">
                    <h1>Special Units</h1>
                </div>
                <div class="cards-container">
                    <!-- Especial Units -->
                    <xsl:for-each select="unit">
                        <div class="flip">
                            <div class="front blue" style="border: 10px solid #395d75;">
                                <img src="{sprite}" alt="{name}" style="filter: drop-shadow(7px 5px 2px rgb(0, 0, 0,0.6))"/>
                                <h2><xsl:value-of select="name"/></h2>
                                <div class="type" style="background:#79b6da; border: 3px solid #395d75;">Special Unit</div>
                            </div>
                            <div class="back blue" style="border: 10px solid #395d75;">
                                <xsl:choose>
                                    <xsl:when test="name = 'Priest'">
                                        <h2><xsl:value-of select="name"/></h2>
                                        <div class="stats" style="background:#da7979; border: 3px solid #804141;">
                                            <h3>Stats</h3>
                                            <div class="statpackage">
                                                <div class="stat" style="background-color: #b15a5a; border: 3px solid #804141;">
                                                    <p>Damage: <xsl:value-of select="base_damage"/></p>
                                                </div>
                                                <div class="stat" style="background-color: #b15a5a; border: 3px solid #804141;">
                                                    <p>Defense: <xsl:value-of select="armour"/></p>
                                                </div> 
                                            </div>
                                        </div>
                                        <div class="stats" style="background:#e2c051;border: 3px solid #77652b;">
                                            <h3>Santification</h3>
                                            <div class="statpackage">
                                                <div class="stat" style="padding: 0px 10px 0px 10px; justify-content: center; align-items: center; display: flex; height: 210px; background-color: #ad933e; border: 3px solid #77652b;">
                                                    <div>
                                                        <h3>Description: </h3>
                                                        <p>The Priest raises their hands to the sky, invoking a celestial blessing on the troops.</p>
                                                        <h3 style="margin-top: 10px;">Effect: </h3>
                                                        <p>Allied units receive a 7% increase in attack and armor stats.</p>
                                                        <h3 style="margin-top: 10px;">Duration: </h3>
                                                        <p>Until Priest dies.</p>
                                                    </div>
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
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <!-- Estructura para otros casos -->
                                        <h2><xsl:value-of select="name"/></h2>
                                        <div class="stats" style="background:#da7979; border: 3px solid #804141;">
                                            <h3>Stats</h3>
                                            <div class="statpackage">
                                                <div class="stat" style="background-color: #b15a5a; border: 3px solid #804141;">
                                                    <p>Damage: <xsl:value-of select="base_damage"/></p>
                                                </div>
                                                <div class="stat" style="background-color: #b15a5a; border: 3px solid #804141;">
                                                    <p>Defense: <xsl:value-of select="armour"/></p>
                                                </div> 
                                            </div>
                                        </div>
                                        <div class="stats" style="background:#e2c051;border: 3px solid #77652b;">
                                            <h3>Chances</h3>
                                            <div class="statpackage">
                                                <div class="stat" style="background-color: #ad933e; border: 3px solid #77652b;">
                                                    <p>Waste: <xsl:value-of select="waste_chance"/>%</p>
                                                </div>
                                                <div class="stat" style="background-color: #ad933e; border: 3px solid #77652b;">
                                                    <p>Hit Again: <xsl:value-of select="attack_again_chance"/>%</p>
                                                </div> 
                                            </div>
                                        </div>
                                        <div class="table">
                                            <h3>Plus Stats</h3>
                                            <table>
                                                <thead>
                                                    <tr>
                                                        <th></th>
                                                        <th>Attack</th>
                                                        <th>Defense</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td>Technology</td>
                                                        <td><xsl:value-of select="plus_stats/attack_technology"/>%</td>
                                                        <td><xsl:value-of select="plus_stats/armour_technology"/>%</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Experience</td>
                                                        <td><xsl:value-of select="plus_stats/attack_experience"/>%</td>
                                                        <td><xsl:value-of select="plus_stats/armour_experience"/>%</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Santified</td>
                                                        <td><xsl:value-of select="plus_stats/attack_sanctified"/>%</td>
                                                        <td><xsl:value-of select="plus_stats/armour_sanctified"/>%</td>
                                                    </tr>
                                                </tbody>
                                            </table>
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
                                    </xsl:otherwise>
                                </xsl:choose>
                            </div>
                        </div>
                    </xsl:for-each>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
