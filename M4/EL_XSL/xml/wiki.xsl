<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head>
                <title>Units and Buildings</title>
            </head>
            <body>
                <h1>Attack Units</h1>
                <xsl:for-each select="root/attack_units/unit">
                    <h2><xsl:value-of select="name"/></h2>
                    <p>Base Damage: <xsl:value-of select="base_damage"/></p>
                    <p>Armour: <xsl:value-of select="armour"/></p>
                    <p>Waste Chance: <xsl:value-of select="waste_chance"/></p>
                    <p>Attack Again Chance: <xsl:value-of select="attack_again_chance"/></p>
                </xsl:for-each>

                <h1>Buildings</h1>
                <xsl:for-each select="root/buildings/building">
                    <h2><xsl:value-of select="name"/></h2>
                    <p>Food Cost: <xsl:value-of select="costs/food_cost"/></p>
                    <p>Wood Cost: <xsl:value-of select="costs/wood_cost"/></p>
                    <p>Iron Cost: <xsl:value-of select="costs/iron_cost"/></p>
                </xsl:for-each>

                <h1>Defence Units</h1>
                <xsl:for-each select="root/defence_units/unit">
                    <h2><xsl:value-of select="name"/></h2>
                    <p>Base Damage: <xsl:value-of select="base_damage"/></p>
                    <p>Armour: <xsl:value-of select="armour"/></p>
                    <p>Waste Chance: <xsl:value-of select="waste_chance"/></p>
                    <p>Attack Again Chance: <xsl:value-of select="attack_again_chance"/></p>
                </xsl:for-each>

                <h1>Special Units</h1>
                <xsl:for-each select="root/special_units/unit">
                    <h2><xsl:value-of select="name"/></h2>
                    <p>Base Damage: <xsl:value-of select="base_damage"/></p>
                    <p>Waste Chance: <xsl:value-of select="waste_chance"/></p>
                    <p>Attack Again Chance: <xsl:value-of select="attack_again_chance"/></p>
                </xsl:for-each>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
