function showInfo(unitId) {
    const infoPanel = document.getElementById('info-panel');
    infoPanel.classList.remove('hidden');

    const units = [
        {
            name: "SWORDSMAN",
            damage: "80",
            armour: "400",
            wasteChance: "55",
            attackAgainChance: "3"
        },
        {
            name: "SPEARMAN",
            damage: "150",
            armour: "1000",
            wasteChance: "65",
            attackAgainChance: "7"
        },
        {
            name: "CROSSBOW",
            damage: "1000",
            armour: "6000",
            wasteChance: "80",
            attackAgainChance: "45"
        },
        {
            name: "CANNON",
            damage: "700",
            armour: "8000",
            wasteChance: "90",
            attackAgainChance: "70"
        },
        {
            name: "ARROWTOWER",
            damage: "80",
            armour: "200",
            wasteChance: "55",
            attackAgainChance: "5"
        },
        {
            name: "CATAPULT",
            damage: "250",
            armour: "1200",
            wasteChance: "65",
            attackAgainChance: "12"
        },
        {
            name: "ROCKETLAUNCHERTOWER",
            damage: "2000",
            armour: "7000",
            wasteChance: "75",
            attackAgainChance: "30"
        },
        {
            name: "MAGICIAN",
            damage: "3000",
            wasteChance: "0",
            attackAgainChance: "75"
        },
        {
            name: "PRIEST",
            damage: "0",
            wasteChance: "0",
            attackAgainChance: "0"
        }
    ];

    const unit = units[unitId - 1];
    document.getElementById('unit-name').textContent = unit.name;
    document.getElementById('unit-damage').textContent = 'Damage: ' + unit.damage;
    document.getElementById('unit-armour').textContent = 'Armour: ' + (unit.armour || 'N/A');
    document.getElementById('unit-waste-chance').textContent = 'Waste Chance: ' + unit.wasteChance;
    document.getElementById('unit-attack-again-chance').textContent = 'Attack Again Chance: ' + unit.attackAgainChance;
}
