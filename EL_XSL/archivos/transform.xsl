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
                                <div class="stats" style="background:#da7979;">
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
                                <div class="stats" style="background:#81da79;">
                                    <h3>Chances</h3>
                                    <div class="statpackage">
                                        <div class="stat" style="background-color: #6bb15a; border: 3px solid #498041;">
                                            <p>Waste: <xsl:value-of select="waste_chance"/>%</p>
                                        </div>
                                        <div class="stat" style="background-color: #6bb15a; border: 3px solid #498041;">
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
                                <div class="stats" style="background:#da7979;">
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
                                <div class="stats" style="background:#81da79;">
                                    <h3>Chances</h3>
                                    <div class="statpackage">
                                        <div class="stat" style="background-color: #6bb15a; border: 3px solid #498041;">
                                            <p>Waste: <xsl:value-of select="waste_chance"/>%</p>
                                        </div>
                                        <div class="stat" style="background-color: #6bb15a; border: 3px solid #498041;">
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
                                <xsl:choose>
                                    <xsl:when test="name = 'Priest'">
                                        <h2><xsl:value-of select="name"/></h2>
                                        <div class="stats" style="background:#da7979;">
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
                                        <div class="stats" style="background:#81da79;">
                                            <h3>Santification</h3>
                                            <div class="statpackage">
                                                <div class="stat" style="text-align: center;background-color: #6bb15a; border: 3px solid #498041;">
                                                    <p>The priest, an expert in divine energy, has the responsibility of sanctifying the ranks of the army, granting a 7% increase to all of them.</p>
                                                </div>
                                            </div>
                                        </div>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <!-- Estructura para otros casos -->
                                        <h2><xsl:value-of select="name"/></h2>
                                        <div class="stats" style="background:#da7979;">
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
                                        <div class="stats" style="background:#81da79;">
                                            <h3>Chances</h3>
                                            <div class="statpackage">
                                                <div class="stat" style="background-color: #6bb15a; border: 3px solid #498041;">
                                                    <p>Waste: <xsl:value-of select="waste_chance"/>%</p>
                                                </div>
                                                <div class="stat" style="background-color: #6bb15a; border: 3px solid #498041;">
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
                                    </xsl:otherwise>
                                </xsl:choose>
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
