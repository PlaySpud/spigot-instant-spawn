package rocks.spud.server.instantspawn;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Provides a simple instant spawn plugin.
 * @author {@literal Johannes Donath <johannesd@torchmind.com>}
 */
public class InstantSpawnPlugin extends JavaPlugin implements Listener {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onEnable () {
		super.onEnable ();

		this.getServer ().getPluginManager ().registerEvents (this, this);
	}

	/**
	 * Handles player damage.
	 * @param event The event.
	 */
	@EventHandler (priority = EventPriority.HIGH)
	public void onPlayerDamage (EntityDamageEvent event) {
		if (event.getEntityType () != EntityType.PLAYER) return;
		if (event.getCause () != EntityDamageEvent.DamageCause.VOID) return;
		Player player = ((Player) event.getEntity ());

		event.setCancelled (true);
		player.damage ((player.getHealth () + 1.0f));
	}

	/**
	 * Handles player deaths.
	 * @param event The event.
	 */
	@EventHandler (priority = EventPriority.HIGH)
	public void onPlayerDeath (PlayerDeathEvent event) {
		event.getEntity ().spigot ().respawn ();
	}
}
