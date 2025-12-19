package blunthorn.flintandstone;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlintAndStone implements ModInitializer {

	public static final String MOD_ID = "flintandstone";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public final Item ITEM = getRegisteredFlintAndStone();

	private Item getRegisteredFlintAndStone(){
		Item.Settings fabricItemSettings = new Item.Settings()
			.maxDamage(1);

		final RegistryKey<Item> registryKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, "flint_and_stone"));
		return Items.register(registryKey, FlintAndStoneItem::new, fabricItemSettings);
	}

	private void registerFlintAndStoneToToolItemGroup(FabricItemGroupEntries entries){
		entries.addBefore(Items.FLINT_AND_STEEL,ITEM);
	}

	@Override
	public void onInitialize() {
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(this::registerFlintAndStoneToToolItemGroup);
		LOGGER.info(MOD_ID + " has loaded item: " + ITEM.toString());
	}
}